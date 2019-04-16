FROM openjdk:8-jdk

RUN apt-get update -y \
&& apt-get upgrade -y \
&& apt-get install gnupg2 -y

RUN apt-key adv --recv-keys --keyserver keyserver.ubuntu.com C2518248EEA14886

RUN apt-get update -y \
&& apt-get install default-jdk -y \
&& apt-get install software-properties-common -y 



RUN apt-get install maven -y

RUN apt-get install git -y

RUN git clone https://github.com/doublean/DevOpsProject.git 
WORKDIR /DevOpsProject

WORKDIR /DevOpsProject

RUN mvn package \
&& java -cp target/Pandas-1.0-SNAPSHOT.jar Project.Devops.Sec.App

