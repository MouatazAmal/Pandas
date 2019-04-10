FROM debian:buster

RUN apt-get update \
&& apt-get install default-jdk \
&& apt-get install software-properties-common \
&& add-apt-repository "deb http://ppa.launchpad.net/webupd8team/java/ubuntu xenial main" \
&& apt-get update \
&& apt-get install oracle-java8-installer 

RUN echo JAVA_HOME="/usr/lib/jvm/java-8-oracle" >> /etc/environment 

RUN apt-get install mvn 

RUN apt-get install git 

RUN git clone https://github.com/doublean/DevOpsProject.git \

WORKDIR /DevOpsProject

RUN mvn package \
&& java -cp target/Pandas-1.0-SNAPSHOT.jar Project.Devops.Sec.App

