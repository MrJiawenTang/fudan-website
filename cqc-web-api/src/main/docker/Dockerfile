FROM frolvlad/alpine-oraclejdk8:slim
# image autor
# MAINTAINER Injedu "company@injedu.com"

ENV WORK_PATH /home/project
ENV APP_NAME @project.name@
ENV APP_VERSION @project.version@

EXPOSE 8080

#USER
#USER user:group

#VOLUME
VOLUME /tmp

#ADD
ADD @project.build.finalName@.@project.packaging@ $WORK_PATH/app.@project.packaging@

# WORKDIR
WORKDIR $WORK_PATH
#ONBUILD
RUN sh -c 'touch app.jar'
# JAVA_OPTS
ENV JAVA_OPTS=""
# ENTRYPOINT 
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar app.jar" ]