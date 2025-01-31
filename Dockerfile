FROM openjdk:17

COPY target/jira-1.0.jar ./jira-1.0.jar
COPY resources ./resources

ENV DB_PASSWORD=JiraRush;
ENV DB_USERNAME=jira;
ENV APP_HOST_URL=http://localhost:8080;
ENV APP_TEST_MAIL=jira4jr@gmail.com;
ENV MAIL_PASSWORD=zdfzsrqvgimldzyj
ENV GITHUB_CLIENT_ID=3d0d8738e65881fff266;
ENV GITHUB_CLIENT_SECRET=0f97031ce6178b7dfb67a6af587f37e222a16120;
ENV GOOGLE_CLIENT_ID=329113642700-f8if6pu68j2repq3ef6umd5jgiliup60.apps.googleusercontent.com;
ENV GOOGLE_CLIENT_SECRET=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;
ENV GITLAB_CLIENT_ID=b8520a3266089063c0d8261cce36971defa513f5ffd9f9b7a3d16728fc83a494;
ENV GITLAB_CLIENT_SECRET=e72c65320cf9d6495984a37b0f9cc03ec46be0bb6f071feaebbfe75168117004
ENV MAIL_PASSWORD=zdfzsrqvgimldzyj

ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "/jira-1.0.jar"]
