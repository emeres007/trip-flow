// This is the Jenkinsfile that will be used to build & test the project.
pipeline {
    agent any
    options {
        skipDefaultCheckout()
    }
    tools {
        maven "mvn"
    }

    environment {
        DOCKER_IMAGE = "emeres/trip-flow"
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', credentialsId: 'Git token', url: 'https://github.com/emeres007/trip-flow.git'
            }
        }
        stage('Build') {
            parallel {
                stage('Java') {
                    steps {
                          sh 'mvn clean install'
                    }
                }
            }
        }

        stage('Test') {
            steps {
                script {
                    sh 'mvn test'
                }
            }
        }

        stage('Build Docker Image') {
             steps {
                  script {
                        sh 'echo "I am in $(pwd)"'

                       docker.build("${DOCKER_IMAGE}:latest")
                  }
              }
          }
          stage('Push to Docker Hub') {
              steps {
                  withCredentials([usernamePassword(credentialsId: 'dockerhub', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
                      sh "echo $PASSWORD | docker login -u $USERNAME --password-stdin"
                      sh "docker push ${DOCKER_HUB}/${IMAGE_NAME}:latest"
                  }
              }
          }
    }
    post {
        success {
            // Actions after the build succeeds
            echo 'Build was successful!'
        }
        failure {
            // Actions after the build fails
            echo 'Build failed. Check logs.'
        }
    }




}