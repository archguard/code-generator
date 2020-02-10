pipeline {
    agent any
    stages {
        def app
        stage('Build') {
            steps {
                sh './gradlew build'
            }
        }
        stage('Build Image') {
            app = docker.build("hule0912/demo")
        }
        stage('Push image') {
            docker.withRegistry('https://registry.hub.docker.com', 'DockerHubCredentials') {
                app.push("${env.BUILD_NUMBER}")
                app.push("latest")
            }
        }
    }
}