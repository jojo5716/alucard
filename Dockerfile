FROM gradle:6.8 as builder

USER root

ENV APP_DIR /app
WORKDIR $APP_DIR

COPY build.gradle.kts $APP_DIR/
COPY settings.gradle.kts $APP_DIR/

RUN gradle dependencies

COPY . $APP_DIR

RUN gradle build -x test

USER guest

# -----------------------------------------------------------------------------

FROM openjdk:13-slim-buster

WORKDIR /app

COPY --from=builder /app/init.sh /app
COPY --from=builder /app/build/libs/alucard*.jar /app/
COPY --from=builder /app/db/ db/

EXPOSE 8000

ENTRYPOINT ["sh", "init.sh"]
