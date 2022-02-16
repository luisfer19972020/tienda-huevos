package com.semana3.tienda.tienda.models.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadServiceImpl implements IUploadService {
    private final static String UPLOADS_FOLDER = "uploads";

    @Override
    public String guardar(MultipartFile file) throws IOException {
        String nombreArchivo = UUID.randomUUID().toString().concat("-" + file.getOriginalFilename());
        Path rootPath = this.getPath(nombreArchivo);
        Files.copy(file.getInputStream(), rootPath);

        return nombreArchivo;
    }

    @Override
    public boolean eliminar(String fileName) {
        Path rootPath = this.getPath(fileName);
        File archivo = rootPath.toFile();
        if (archivo.exists() && archivo.canRead()) {
            return archivo.delete();
        }
        return false;
    }

    public Path getPath(String filename) {
        return Paths.get(UPLOADS_FOLDER).resolve(filename).toAbsolutePath();
    }
}
