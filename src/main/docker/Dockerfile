FROM f00lisher/alpn-jre8-cst
VOLUME /tmp
ADD demo-0.0.1-SNAPSHOT.jar app.jar

RUN bash -c 'touch /app.jar'
ENTRYPOINT exec java -server -Xms1024m -Xmx1024m -Djava.security.egd=file:/dev/./urandom $JAVA_OPTS -jar app.jar

EXPOSE 8088