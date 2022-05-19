#!/bin/bash
NAME=main-auth

kubectl delete hpa $NAME
kubectl autoscale deployment $NAME --cpu-percent=50 --min=1 --max=3

