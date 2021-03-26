@echo off
for /f %%q in ("%~dp0.") do set dir_name=%%~nxq
echo on
mvnw.cmd package -Pnative -Dquarkus.native.container-build=true && docker build -f src/main/docker/Dockerfile.native-distroless -t quarku && docker image prune -f && docker run -i --rm -p 8080:8080 quarkus/%dir_name%