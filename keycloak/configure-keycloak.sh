#!/bin/bash

set -e

echo "Logando no Keycloak CLI..."

/opt/keycloak/bin/kcadm.sh config credentials \
  --server http://localhost:8080 \
  --realm master \
  --user admin \
  --password admin

echo "Criando Realm 'tokenManagement'..."
/opt/keycloak/bin/kcadm.sh create realms \
  -s realm=tokenManagement \
  -s enabled=true

echo "Criando Client 'clientIdTest' com secret fixo..."
/opt/keycloak/bin/kcadm.sh create clients -r tokenManagement \
  -s clientId=clientIdTest \
  -s enabled=true \
  -s protocol=openid-connect \
  -s publicClient=false \
  -s serviceAccountsEnabled=true \
  -s clientAuthenticatorType=client-secret \
  -s secret="clientSecretTest"

echo "Configuração concluída!"
