#!/bin/bash
docker run -d -p 8080:8080 -v /opt/previewoffice/data:/logistics --privileged=true --name previewoffice previewoffice-0.0.12