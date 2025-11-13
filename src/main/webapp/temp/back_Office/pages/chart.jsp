<%--
  Created by IntelliJ IDEA.
  User: Toky
  Date: 07/01/2025
  Time: 10:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<!-- Chart Start -->
<div class="container-fluid pt-4 px-4">
    <div class="row g-4">
        <!-- Single Line Chart -->
        <div class="col-sm-12 col-xl-6">
            <div class="bg-light rounded h-100 p-4">
                <h6 class="mb-4">Single Line Chart</h6>
                <canvas id="singleLineChart"></canvas>
            </div>
        </div>
        <!-- Multiple Line Chart -->
        <div class="col-sm-12 col-xl-6">
            <div class="bg-light rounded h-100 p-4">
                <h6 class="mb-4">Multiple Line Chart</h6>
                <canvas id="multipleLineChart"></canvas>
            </div>
        </div>
        <!-- Single Bar Chart -->
        <div class="col-sm-12 col-xl-6">
            <div class="bg-light rounded h-100 p-4">
                <h6 class="mb-4">Single Bar Chart</h6>
                <canvas id="singleBarChart"></canvas>
            </div>
        </div>
        <!-- Multiple Bar Chart -->
        <div class="col-sm-12 col-xl-6">
            <div class="bg-light rounded h-100 p-4">
                <h6 class="mb-4">Multiple Bar Chart</h6>
                <canvas id="multipleBarChart"></canvas>
            </div>
        </div>
        <!-- Pie Chart -->
        <div class="col-sm-12 col-xl-6">
            <div class="bg-light rounded h-100 p-4">
                <h6 class="mb-4">Pie Chart</h6>
                <canvas id="pieChart"></canvas>
            </div>
        </div>
        <!-- Doughnut Chart -->
        <div class="col-sm-12 col-xl-6">
            <div class="bg-light rounded h-100 p-4">
                <h6 class="mb-4">Doughnut Chart</h6>
                <canvas id="doughnutChart"></canvas>
            </div>
        </div>
    </div>
</div>
<!-- Chart End -->
<script>
    // Generate random data for charts
    function generateRandomData(count, min = 0, max = 100) {
        return Array.from({ length: count }, () => Math.floor(Math.random() * (max - min + 1)) + min);
    }

    // Labels for all charts
    const labels = ['January', 'February', 'March', 'April', 'May', 'June', 'July'];

    // Single Line Chart
    new Chart(document.getElementById('singleLineChart'), {
        type: 'line',
        data: {
            labels: labels,
            datasets: [{
                label: 'Dataset 1',
                data: generateRandomData(labels.length),
                borderColor: 'rgba(75, 192, 192, 1)',
                tension: 0.1
            }]
        }
    });

    // Multiple Line Chart
    new Chart(document.getElementById('multipleLineChart'), {
        type: 'line',
        data: {
            labels: labels,
            datasets: [
                {
                    label: 'Dataset 1',
                    data: generateRandomData(labels.length),
                    borderColor: 'rgba(75, 192, 192, 1)',
                    tension: 0.1
                },
                {
                    label: 'Dataset 2',
                    data: generateRandomData(labels.length),
                    borderColor: 'rgba(255, 99, 132, 1)',
                    tension: 0.1
                }
            ]
        }
    });

    // Single Bar Chart
    new Chart(document.getElementById('singleBarChart'), {
        type: 'bar',
        data: {
            labels: labels,
            datasets: [{
                label: 'Dataset 1',
                data: generateRandomData(labels.length),
                backgroundColor: 'rgba(75, 192, 192, 0.2)',
                borderColor: 'rgba(75, 192, 192, 1)',
                borderWidth: 1
            }]
        }
    });

    // Multiple Bar Chart
    new Chart(document.getElementById('multipleBarChart'), {
        type: 'bar',
        data: {
            labels: labels,
            datasets: [
                {
                    label: 'Dataset 1',
                    data: generateRandomData(labels.length),
                    backgroundColor: 'rgba(75, 192, 192, 0.2)',
                    borderColor: 'rgba(75, 192, 192, 1)',
                    borderWidth: 1
                },
                {
                    label: 'Dataset 2',
                    data: generateRandomData(labels.length),
                    backgroundColor: 'rgba(255, 99, 132, 0.2)',
                    borderColor: 'rgba(255, 99, 132, 1)',
                    borderWidth: 1
                }
            ]
        }
    });

    // Pie Chart
    new Chart(document.getElementById('pieChart'), {
        type: 'pie',
        data: {
            labels: labels.slice(0, 5),
            datasets: [{
                data: generateRandomData(5),
                backgroundColor: ['rgba(75, 192, 192, 0.2)', 'rgba(255, 99, 132, 0.2)', 'rgba(255, 205, 86, 0.2)', 'rgba(54, 162, 235, 0.2)', 'rgba(153, 102, 255, 0.2)']
            }]
        }
    });

    // Doughnut Chart
    new Chart(document.getElementById('doughnutChart'), {
        type: 'doughnut',
        data: {
            labels: labels.slice(0, 5),
            datasets: [{
                data: generateRandomData(5),
                backgroundColor: ['rgba(75, 192, 192, 0.2)', 'rgba(255, 99, 132, 0.2)', 'rgba(255, 205, 86, 0.2)', 'rgba(54, 162, 235, 0.2)', 'rgba(153, 102, 255, 0.2)']
            }]
        }
    });
</script>
