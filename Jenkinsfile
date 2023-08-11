pipeline {
    agent any

    tools {
        gradle "gradle"
    }

    stages {
        stage('Compile') {
            steps {
                sh "gradle classes"
            }
        }
        stage('Test') {
            steps {
                    sh "gradle test"
                }
        }
        
        
        // stage('Package') {
        //     steps {
        //             sh "./gradlew build"
        //         }
        // }
        
        stage('Build') {
            steps {
                        sh "gradle clean build"
                }
        }

        stage('Build docker image') {
            steps {

                sh "docker build -t authentication_service:latest ."
            }
        }
    }
}
