package com.semana3.tienda.tienda.models.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface IUploadService {

    public String guardar(MultipartFile file) throws IOException;

    public boolean eliminar(String fileName);

}
