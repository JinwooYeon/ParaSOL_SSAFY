#!/bin/bash
NAME=main-login

kubectl delete hpa $NAME
kubectl autoscale deployment $NAME --cpu-percent=50 --min=1 --max=3

