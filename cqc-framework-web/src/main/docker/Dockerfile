FROM frolvlad/alpine-oraclejdk8:slim
# image autor
# MAINTAINER Injedu "company@injedu.com"

ENV WORK_PATH /home/project
ENV APP_NAME @project.build.finalName@.@project.packaging@
ENV APP_VERSION @project.version@

EXPOSE 8080

#USER
#USER user:group

#VOLUME
VOLUME /tmp

#ADD
ADD $APP_NAME $WORK_PATH/app.@project.packaging@

#LABEL
#STOPSIGNAL
#ARG
#ONBUILD
RUN sh -c 'touch /app.jar'
# WORKDIR
WORKDIR $WORK_PATH
# JAVA_OPTS
ENV JAVA_OPTS=""
# ENTRYPOINT 
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]