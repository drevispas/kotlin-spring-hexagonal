#!/bin/bash

if [[ -n "$CONF_ENV" ]]; then
  export CONFIG_RESOURCE_FILENAME="application-${CONF_ENV}.conf"
  echo HOCON config filname is set to "$CONFIG_RESOURCE_FILENAME" by CONF_ENV environment variable.
else
  export CONFIG_RESOURCE_FILENAME="application-local.conf"
  echo HOCON config filname is set to "$CONFIG_RESOURCE_FILENAME" by default.
  echo This server should set the environment variable [CONF_ENV] for running correctly.
fi

JVM_INITIAL_RAM_PERCENTAGE=""
JVM_MIN_RAM_PERCENTAGE=""
JVM_MAX_RAM_PERCENTAGE=""
JVM_MAX_METASPACE_MEMORY=""

if [[ -n "$CONF_JVM_INITIAL_RAM_PERCENTAGE" ]]; then
  JVM_INITIAL_RAM_PERCENTAGE="-XX:InitialRAMPercentage=$CONF_JVM_INITIAL_RAM_PERCENTAGE"
  echo initial ram percentage: "$JVM_INITIAL_RAM_PERCENTAGE"
else
  echo "Environment variable [CONF_JVM_INITIAL_RAM_PERCENTAGE] is not defined. To add jvm options as -XX:InitialRAMPercentage set the environment variable. ex: 25"
fi

if [[ -n "$CONF_JVM_MIN_RAM_PERCENTAGE" ]]; then
   JVM_MIN_RAM_PERCENTAGE="-XX:MinRAMPercentage=$CONF_JVM_MIN_RAM_PERCENTAGE"
   echo min ram percentage: "$JVM_MIN_RAM_PERCENTAGE"
else
  echo "Environment variable [CONF_JVM_MIN_RAM_PERCENTAGE] is not defined. To add jvm options as -XX:MinRAMPercentage set the environment variable. ex: 50"
fi

if [[ -n "$CONF_JVM_MAX_RAM_PERCENTAGE" ]]; then
   JVM_MAX_RAM_PERCENTAGE="-XX:MaxRAMPercentage=$CONF_JVM_MAX_RAM_PERCENTAGE"
   echo max ram percentage: "$JVM_MAX_RAM_PERCENTAGE"
else
  echo "Environment variable [CONF_JVM_MAX_RAM_PERCENTAGE] is not defined. To add jvm options as -XX:MaxRAMPercentage set the environment variable. ex: 50"
fi

if [[ -n "$CONF_JVM_MAX_METASPACE_MEMORY" ]]; then
   JVM_MAX_METASPACE_MEMORY="-XX:MaxMetaspaceSize=${CONF_JVM_MAX_METASPACE_MEMORY}"
   echo max metaspace size: "$JVM_MAX_METASPACE_MEMORY"
else
  echo "Environment variable [CONF_JVM_MAX_METASPACE_MEMORY] is not defined. To add jvm options as -XX:MaxMetaspaceSize set the environment variable. ex: 256m"
fi

export JAVA_OPTS="$JAVA_OPTS \
        -server \
        -Djava.security.egd=file:/dev/./urandom \
        -Dconfig.resource=${CONFIG_RESOURCE_FILENAME} \
        -XX:+UseContainerSupport \
        ${JVM_INITIAL_RAM_PERCENTAGE} \
        ${JVM_MIN_RAM_PERCENTAGE} \
        ${JVM_MAX_RAM_PERCENTAGE} \
        ${JVM_MAX_METASPACE_MEMORY} \
        -XX:+UseG1GC -XX:MaxGCPauseMillis=200 -XX:G1HeapRegionSize=8m -XX:+ParallelRefProcEnabled -XX:-ResizePLAB \
        -XX:+HeapDumpOnOutOfMemoryError"
