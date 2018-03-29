sudo docker stop app
sudo docker stop banco
sudo docker rm app
sudo docker rm banco
sudo docker rmi -f docker-jsf/app
sudo docker rmi -f docker-jsf/banco
