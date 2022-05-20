#!/bin/bash
NAME=main-account-history

kubectl delete hpa $NAME
kubectl autoscale deployment $NAME --cpu-percent=10 --min=1 --max=3

