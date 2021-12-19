package com.silencer.imageapi.upload.repository

import org.apache.logging.log4j.util.Strings
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import software.amazon.awssdk.core.sync.RequestBody
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.PutObjectRequest
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*


@Component
class S3Uploader {

    fun upload(multipartFile: MultipartFile): String {
        val s3 = S3Client.builder()
            .region(Region.AP_NORTHEAST_2)
            .build()

        val uploadPath = computePath(multipartFile.originalFilename ?: makeRandomUuidFileName())

        val objectRequest = PutObjectRequest.builder()
            .bucket("silencer-image-api-s3")
            .key(uploadPath)
            .build()

        s3.putObject(objectRequest, RequestBody.fromBytes(multipartFile.bytes))

        return uploadPath;
    }

    private fun makeRandomUuidFileName(extension: String = Strings.EMPTY) = UUID.randomUUID().toString() + extension

    private fun computePath(fileName: String) = "/" + LocalDate.now().format(DateTimeFormatter.ofPattern("/yyyy/MM/dd/")) + fileName
}