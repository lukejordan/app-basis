#!
export TOMCAT_HOME=~/install/apache-tomcat-8.0.28
export APP_PATH=~/Development/sideproject/app-basis
export APP_NAME=app-webapp
export WAR_OLD_NAME=app-webapp
export WAR_NAME=basis

echo "Stop tomcat"
${TOMCAT_HOME}/bin/shutdown.sh

echo "Build Application"
cd ${APP_PATH}/${APP_NAME}
mvn clean package -Dmaven.test.skip

echo "Clean webapp from tomcat webapps"
rm -rf ${TOMCAT_HOME}/libexec/webapps/${WAR_NAME}*
echo "Clean tomcat logs"
rm -rf ${TOMCAT_HOME}/libexec/logs/*

echo "Deploy webapp"
cp target/${WAR_OLD_NAME}*.war ${TOMCAT_HOME}/webapps/${WAR_NAME}.war

echo "Start tomcat"
${TOMCAT_HOME}/bin/startup.sh
