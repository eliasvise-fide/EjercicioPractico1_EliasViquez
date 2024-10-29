/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EliasViquezExam.demo.service.impl;

import com.google.auth.Credentials;
import com.google.auth.ServiceAccountSigner;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.StorageOptions;
import com.google.cloud.storage.Storage.SignUrlOption;
import com.google.cloud.storage.StorageOptions;
import EliasViquezExam.service.FirebaseStorageService;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.concurrent.TimeUnit;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Jonathan
 */

@Service
public class FirebaseStorageServiceImpl implements FirebaseStorageService {

    @Override
    public String cargaImagen(MultipartFile archivoLocalCliente, String carpeta, Long id) {
        try {
            // El nombre original del archivo local del cliente
            String extension = archivoLocalCliente.getOriginalFilename();

            // Se genera el nombre según el código del artículo.
            String fileName = "img" + sacaNumero(id) + extension;

            // Se convierte/sube el archivo a un archivo temporal
            File file = this.convertToFile(archivoLocalCliente);

            // Se copia a Firestore y se obtiene el URL válido de la imagen (por 10 años)
            String URL = this.uploadFile(file, carpeta, fileName);

            // Se elimina el archivo temporal cargado desde el cliente
            file.delete();

            return URL;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

// Method to upload a file
    private String uploadFile(File file, String carpeta, String fileName) throws IOException {
        // Define the location and access to the .jasper file
        ClassPathResource json = new ClassPathResource(rutaJsonFile + File.separator + archivoJsonFile);
        BlobId blobId = BlobId.of(BucketName, rutaSuperiorStorage + "/" + carpeta + "/" + fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
                .setContentType("media")
                .build();

        Credentials credentials = GoogleCredentials
                .fromStream(json.getInputStream());
        Storage storage = StorageOptions.newBuilder()
                .setCredentials(credentials).build().getService();
        storage.create(blobInfo, Files.readAllBytes(file.toPath()));
        String url = storage.signUrl(blobInfo, 3650, TimeUnit.DAYS,
                Storage.SignUrlOption.signWith((ServiceAccountSigner) credentials))
                .toString();
        return url;
    }

// Utility method to convert the file from the user's local machine
// to a temporary file on the server
    private File convertToFile(MultipartFile archivoLocalCliente) throws IOException {
        File tempFile = File.createTempFile("img", null);
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(archivoLocalCliente.getBytes());
            fos.close();
        }
        return tempFile;
    }

// Utility method to get a string with leading zeros...
    private String sacaNumero(long id) {
        return String.format("%010d", id);
    }
}
