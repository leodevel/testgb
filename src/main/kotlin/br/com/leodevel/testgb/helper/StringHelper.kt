package br.com.leodevel.testgb.helper

fun String?.removeCpfFormatting() =
    this?.replace(".", "")
        ?.replace(",", "")
        ?.replace("-", "")
        ?.trim()