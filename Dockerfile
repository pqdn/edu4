FROM bellsoft/liberica-openjre-alpine:17.0.9-11

EXPOSE 8080/tcp
EXPOSE 9875/tcp

COPY edu-service/build/libs/edu*.jar edu.jar

ENTRYPOINT [ \
"java", \
"-jar", \
"-Xmx1536m", \
"-XX:+CrashOnOutOfMemoryError", \
"/edu.jar" \
]
