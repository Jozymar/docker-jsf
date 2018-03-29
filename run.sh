sudo docker build -t docker-jsf/banco ./postgres
sudo docker build -t docker-jsf/app .

sudo docker run -d --name banco docker-jsf/banco
sudo docker run -p 8081:8080 -d --name app --link banco:host-postgres docker-jsf/app

