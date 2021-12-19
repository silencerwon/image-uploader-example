package com.silencer.imageapi.upload.controller

import com.silencer.imageapi.upload.response.UploadResponse
import com.silencer.imageapi.upload.service.S3UploadService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
class UploadController(
    val s3UploadService: S3UploadService
) {
    @PostMapping("/upload")
    fun upload(multipartFile: MultipartFile) = UploadResponse(s3UploadService.upload(multipartFile))
}