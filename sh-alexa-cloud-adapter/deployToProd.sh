#!/bin/sh

echo 1. Build jar
mvn clean install
STATUS=$?

if [ $STATUS -eq 0 ]; then
echo "Build Successful"
else
echo "Build Failed"
exit 1
fi

echo 2. Deploy to prod server


#scp /Users/denys/cert/private-key.pem myprod:cert/
#scp /Users/denys/cert/configuration.cnf myprod:cert/
#scp /Users/denys/cert/certificate.pem myprod:cert/

scp target/alexa-0.0.1-SNAPSHOT.jar myprod:server/
