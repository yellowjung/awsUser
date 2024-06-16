# AWS User Get Project

이 프로젝트는 AWS IAM 액세스 키 관리를 자동화하는 Spring Boot 애플리케이션입니다.

### 사용 목적
> Access Key Pair를 생성한지 N시간을 초과하는 IAM User의
> User ID와 Access Key ID 반환하는게 목적입니다.

## 요구사항

- Docker
- Kubernetes 클러스터 or Minikube
- kubectl
- base64 인코딩 도구 (예: Linux/macOS의 base64 명령)
- AWS CLI (Local에서 실행할 경우)

## 설치 및 실행

### 로컬 환경에서 실행
0. AWS CLI가 사전에 준비되어 Access key ID와 Secret access key가 config로 등록되어있어야합니다.
``` bash
https://docs.aws.amazon.com/ko_kr/cli/latest/userguide/getting-started-install.html
참고하세요
```
1. 프로젝트를 클론합니다.
```bash
git clone https://github.com/yellowjung/awsUser
cd awsUser
```
2. Maven을 사용하여 빌드합니다.
```bash
mvn clean package
```
3. 생성된 JAR 파일을 실행합니다.
```bash
java -jar target/.jar
```

## 사용 예제

### IAM 사용자의 오래된 액세스 키 조회

Access Key Pair 를 생성한지 N시간을 초과하는 IAM
User의 User ID와 Access Key ID를 조회하는 예제입니다.

```bash
GET /old-access-keys?hours=24
```

## DockerFile 사용 방법

### Docker build
docker 혹은 podman이 설치되어있는 환경에서 아래의 명령을 실행합니다.
명령어 실행 위치는 root dir입니다.
```bash
docker
docker build -t {image_name}:{tag} .

podman
podman build -t {image_name}:{tag} .
```

### Docker run
생성한 이미지를 수행시킵니다.
이 때 미리 발급 받은 AWS_ACCESS_KEY_ID, 및 AWS_SECRET_ACCESS_KEY를 
환경변수로 입력합니다.
```bash
docker run -e AWS_ACCESS_KEY_ID={AWS_ACCESS_KEY_ID} \
           -e AWS_SECRET_ACCESS_KEY={AWS_SECRET_ACCESS_KEY} \
           -p 8080:8080 {image_name}:{tag}
```

## Kubernetes 배포

### AWS 자격 증명 설정
AWS Access key ID와 Secret Access Key를 base64로 인코딩합니다.
```bash
echo -n 'aws_access_key_id' | base64
echo -n 'aws_secret_access_key' | base64
```

### k8s-manifest.yaml 업데이트
k8s-manifest.yaml파일에 인코딩 된 값을 항목에 맞추어 업데이트합니다.

### Kubernetes 리소스 적용
Kubernetes 클러스터에 리소스를 적용합니다.
```bash
kubectl apply -f k8s-manifest.yaml
```

### 애플리케이션 접근
애플리케이션이 성공적으로 배포되면, Node의 IP주소와 Nodeport를 사용하여 애플리케이션에 접근 할 수 있습니다
```bash
http://<NodeIP>:31001
```

## 주의 사항
### 'imagePullPolicy: Never'
  - 해당 옵션은 로컬 Docker 레지스트리에 있는 이미지를 사용하도록 설정합니다. 만약 외부의 이미지를 사용하려면 해당 옵션을 변경해야합니다.

### Minikube의 경우 
1. Minikube는 Nodeport 사용시 tunnel을 사용하여 접근이 가능합니다.
2. Service가 정상적으로 올라와있고 Nodeport가 정상적으로 표기 되었으면 다음과 같은 명령어를 적어주세요
    ```bash
    minikube service aws-user-app --url
    ```
3. 다음과 같이 출력이 나오면 해당 Port(ex. 5027)로 접근을 하면 서비스를 이용 할 수 있습니다.
    ```bash
    http://127.0.0.1:5027
    ```