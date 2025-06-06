import matplotlib.pyplot as plt

data = {
    "Port of Busan": 1.662208252e7,
    "Port of Shanghai": 1.729658498e7,
    "Port of Singapore": 1.688202719e7,
    "Port of Tianjin": 1.783813995e7,
    "Port of Tokyo": 1.687510945e7
}

plt.figure(figsize=(10,6))
plt.bar(data.keys(), data.values(), color='teal')
plt.xticks(rotation=15, ha='right')
plt.ylabel('Total Shipment Weight (kg)')
plt.title('Total Shipment Weight per Port')
plt.tight_layout()
plt.savefig("shipment_weight_chart.png")
plt.show()
