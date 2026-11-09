# spring-boot-image-resize

[![Java CI with Maven](https://github.com/hendisantika/spring-boot-image-resize/actions/workflows/maven.yml/badge.svg)](https://github.com/hendisantika/spring-boot-image-resize/actions/workflows/maven.yml)
![Java](https://img.shields.io/badge/Java-25-orange?logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.16-6DB33F?logo=springboot)
![AWS SDK](https://img.shields.io/badge/AWS%20SDK-v2-FF9900?logo=amazonaws)
[![Dependabot](https://img.shields.io/badge/Dependabot-enabled-025E8C?logo=dependabot)](.github/dependabot.yml)

On-the-fly image resizer built with Spring Boot. Images are uploaded to an AWS S3 bucket and served
resized (thumbnail / detail) on request.

## Features

- Upload an image to AWS S3 through a simple web form (Thymeleaf).
- Resize an image on request into predefined sizes, keeping the aspect ratio:

  | Type        | Size (px) |
  |-------------|-----------|
  | `thumbnail` | 200 × 200 |
  | `detail`    | 400 × 400 |

- Serve an already optimized image if it exists, otherwise resize the original on the fly.

## Tech Stack

- Java 25 (Temurin)
- Spring Boot 3.5.16 (Web, Thymeleaf, Configuration Processor, DevTools)
- AWS SDK for Java v2 (S3)
- Lombok
- Maven (wrapper included) & JUnit 5

## Requirements

- JDK 25
- (Optional) Maven 3.9+ — or just use the bundled `./mvnw`
- An AWS account & S3 bucket (only needed for uploading/serving images)

## Configuration

All settings live in `src/main/resources/application.yml` and can be overridden with environment variables.
**Never commit real AWS keys.** Credentials are resolved by the AWS SDK
[default credentials provider chain](https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/credentials-chain.html)
(`AWS_ACCESS_KEY_ID` / `AWS_SECRET_ACCESS_KEY`, `~/.aws/credentials`, IAM role, ...).

| Environment variable    | Description                    | Default          |
|-------------------------|--------------------------------|------------------|
| `AWS_ACCESS_KEY_ID`     | AWS access key (SDK)           | –                |
| `AWS_SECRET_ACCESS_KEY` | AWS secret key (SDK)           | –                |
| `S3_BUCKET`             | Target S3 bucket name          | `my-bucket`      |
| `S3_REGION`             | S3 region                      | `ap-southeast-1` |

Max upload size is `1GB` (`spring.servlet.multipart.*`).

## Build, Test & Run

```bash
# build & run all tests
./mvnw clean verify

# run the application
export AWS_ACCESS_KEY_ID=...
export AWS_SECRET_ACCESS_KEY=...
export S3_BUCKET=my-bucket
export S3_REGION=ap-southeast-1
./mvnw spring-boot:run

# or run the packaged jar
java -jar target/image-resizer-0.0.1-SNAPSHOT.jar
```

The application starts on <http://localhost:8080>.

## Endpoints

| Method | Path                                      | Description                                               |
|--------|-------------------------------------------|-----------------------------------------------------------|
| `GET`  | `/`                                       | Upload form                                               |
| `POST` | `/upload`                                 | Upload an image (`multipart/form-data`, field `file`) to S3 |
| `GET`  | `/image/show/{typeName}/seo/{fileName}`   | Return the resized image (`image/jpeg`); `typeName` = `thumbnail` or `detail` |

Example:

```bash
curl -F "file=@photo.jpg" http://localhost:8080/upload
curl -o thumb.jpg http://localhost:8080/image/show/thumbnail/seo/photo.jpg
```

## CI/CD & Dependency Updates

- **GitHub Actions** — [`.github/workflows/maven.yml`](.github/workflows/maven.yml) builds and tests the project
  on JDK 25 for every push and pull request to `main`, and uploads the test reports and the jar as artifacts.
- **Dependabot** — [`.github/dependabot.yml`](.github/dependabot.yml) checks Maven dependencies and GitHub Actions
  weekly (Monday 06:00 WIB), grouping Spring Boot, AWS SDK, other minor/patch Maven updates and Actions updates
  into separate pull requests.

## Author

**Hendi Santika**

- Email: hendisantika@gmail.com
- Telegram: [@hendisantika34](https://t.me/hendisantika34)
- Link: [s.id/hendisantika](https://s.id/hendisantika)
