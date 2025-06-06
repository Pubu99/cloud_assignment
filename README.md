# 📦 Cloud Computing - Shipment Weight Analysis using Hadoop MapReduce

This project demonstrates how to use **Hadoop MapReduce** to analyze shipment weight data across major Asian ports. The application reads a CSV file with shipping records, maps each shipment to its respective port, and calculates the **total shipment weight per port**.

---

## ⚙️ Prerequisites

Ensure the following are installed on your machine:

- [Docker](https://www.docker.com/)
- [docker-hadoop](https://github.com/big-data-europe/docker-hadoop) or similar Hadoop Docker image
- Basic knowledge of Java and Hadoop commands
- Dataset Link - [Dataset](https://www.kaggle.com/datasets/mikoajfish99/port-of-los-angeles/data)
- The Data set also available with the files

---

## 🚀 How to Run

### 1. Clone the Repository

```bash
git clone https://github.com/Pubu99/cloud_assignment.git
cd cloud_assignment
```

### 2. Start Hadoop Cluster via Docker

Assuming you're using `docker-hadoop`:

```bash
cd docker-hadoop
docker-compose up -d
```

Wait until the cluster is fully started (especially the `namenode`, `datanode`, and `resourcemanager` containers).

### 3. Copy Java Files into Hadoop Namenode

```bash
docker cp ShipmentWeightMapper.java namenode:/tmp/
docker cp ShipmentWeightReducer.java namenode:/tmp/
docker cp ShipmentWeightDriver.java namenode:/tmp/
```

### 4. Compile Java Files and Create JAR

```bash
docker exec -it namenode /bin/bash
cd /tmp
mkdir shipment_classes
javac -classpath $(hadoop classpath) -d shipment_classes ShipmentWeightMapper.java ShipmentWeightReducer.java ShipmentWeightDriver.java
jar -cvf shipment_weight.jar -C shipment_classes/ .
```

### 5. Load Dataset into HDFS

From inside the container:

```bash
hdfs dfs -mkdir -p /user/root/input
hdfs dfs -put /tmp/shipping_data.csv /user/root/input/
```

### 6. Run the MapReduce Job

```bash
hadoop jar /tmp/shipment_weight.jar ShipmentWeightDriver /user/root/input /user/root/output
```

### 7. View the Output

```bash
hdfs dfs -cat /user/root/output/part-r-00000
```

You should see results like:

```
Port Name 1     1.66E7
Port Name 2     1.73E7
...
```

## 📊 Optional: Visualize the Output (Python)

use visualize.py to see the final results in vizualized format.
---

## 📄 License

This project is part of an academic cloud computing assignment. You are free to fork, reference, or modify with credit.

---
