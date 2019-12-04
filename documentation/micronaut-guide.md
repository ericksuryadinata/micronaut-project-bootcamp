# MICRONAUT GUIDE

Dokumentasi ini akan menjelaskan secara global bagaimana membuat masing - masing service yang akan dibuat

## KEBUTUHAN UTAMA

Yang pasti dibutuhkan yaitu micronaut nya

### INSTALASI

#### WINDOWS

1. Download binary dari [sini](https://github.com/micronaut-projects/micronaut-core/releases/download/v1.2.7/micronaut-1.2.7.zip)
2. Extract file yang telah didownload tersebut kemudian pindahkan ke `C:\Program Files\`
3. Kemudian pada Environtment Variables nya windows, tambahkan folder nya micronaut nya ke `PATH`
4. Buka `cmd` kemudian ketikan command `mn --version`

#### LINUX

1. Dengan menggunakan [SDKMAN](https://sdkman.io/install), kita dapat mudah menginstall Micronaut
2. Kemudian baca [dokumentasi](https://github.com/micronaut-projects/micronaut-core/releases/download/v1.2.7/micronaut-1.2.7.zip)

## MEMBUAT SERVICE

1. Pada `root` folder tulis command `mn create-app praxis.religi.auth-service`, folder `auth-service` akan tergenerate
2. Begitupun selanjutnya untuk service - service yang lain
