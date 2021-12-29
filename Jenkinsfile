// Scripted pipeline 
//node{
//	stage('Test'){
//		echo "Test"
//	}
	
//	stage('build'){
//		echo "build"
//	}
//}

// Declarative Pipeline

pipeline {

    agent any
    
    environment{
        dockerHome = tool 'Docker Configured'
        mavenHome = tool 'Maven'
        PATH = "$dockerHome/bin:$mavenHome/bin:$PATH"
        //echo "environment -path  ----> $PATH"
    }
    stages{
        stage('Environment variable values'){
            steps{
            	sh "mvn --version"
            	sh "docker version"
                echo "PATH - $PATH"
                echo "BUILD_NUMBER - $env.BUILD_NUMBER"
                echo "BUILD_ID - $env.BUILD_ID"
                echo "JOB_NAME - $env.JOB_NAME"
                echo "BUILD_TAG - $env.BUILD_TAG"
            }
        }
        stage('clean stage'){
            steps{
                echo "clean stage"
                sh "mvn clean"
            }
        }
        stage('compile stage'){
            steps{
                echo "compile stage"
                sh "mvn compile"
            }            
        }
        stage('build stage'){
            steps{
                echo "build stage"
                sh "mvn package -DskipTests"
            }
        }

        stage('Building Docker Image'){
            steps{
                echo "Building Docker Image"
                echo "docker build -t 1332117977/$env.JOB_NAME:$env.BUILD_TAG . "
                //sh "docker build -t 1332117977/$env.JOB_NAME:$env.BUILD_TAG . "
                script{
                    dockerImage = docker.build("1332117977/${env.JOB_NAME}:${env.BUILD_TAG}")
                }
            }
        }
	    
	stage('Running Docker Image'){
            steps{
                echo "Running Docker Image"
                echo "docker build -t 1332117977/$env.JOB_NAME:$env.BUILD_TAG . "
                //sh "docker build -t 1332117977/$env.JOB_NAME:$env.BUILD_TAG . "
                script{
                    dockerImage.withRun('-p 8090:8090')
                }
            }
        }

        stage('Pushing Docker Image'){
            steps{
                echo "Pushing Docker Image"
                // docker build -t 1332117977/$env.JOB_NAME:$env.BUILD_TAG
                script{
                    docker.withRegistry(' ','dockerhub'){
                        dockerImage.push()
                        dockerImage.push('latest')
                    }                    
                }
            }
        }
    }

    post{
        success{
            echo "successfully build !!!!!!"
        }
        failure{
            echo "failure happened !!!!!!!"
        }
        always{
            echo "I'm always executed!!!!!!"
        }
        changed{
            echo "build status changed !!!!!"
        }
    }
}
