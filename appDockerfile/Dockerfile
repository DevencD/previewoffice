FROM registry.cn-hangzhou.aliyuncs.com/devenc-public/docker_openoffice:4.1.6
VOLUME /tmp
ADD previewoffice-0.0.12-SNAPSHOT.jar /app.jar
ADD supervisord.conf /etc/supervisord.conf
CMD ["/usr/bin/supervisord"]