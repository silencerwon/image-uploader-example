package com.silencer.imageapi.upload.service

import com.silencer.imageapi.upload.repository.S3Uploader
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class S3UploadService(
    val s3Uploader: S3Uploader
) {
    fun upload(multipartFile: MultipartFile) = s3Uploader.upload(multipartFile)
}