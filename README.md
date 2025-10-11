helm uninstall camunda -n camunda  # Remove previous release
kubectl delete namespace camunda   # Optional: clean namespace
kubectl create namespace camunda

[1] CONFIGURE CLUSTER
kind create cluster --name camunda-platform-local

[2] DOWNLOAD HELM
helm repo add camunda https://helm.camunda.io
helm repo update

[3] helm install camunda-platform camunda/camunda-platform -f values-local.yaml


[4] PORT FORWARDING
{code}
nohup kubectl port-forward svc/camunda-platform-tasklist 8081:80 --address 0.0.0.0 > /dev/null 2>&1 &
nohup kubectl port-forward svc/camunda-platform-operate 8082:80 --address 0.0.0.0 > /dev/null 2>&1 &
nohup kubectl port-forward svc/camunda-platform-zeebe-gateway 26500:26500 --address 0.0.0.0 > /dev/null 2>&1 &
{code}


[5] WHY do I need -address 0.0.0.0
WSL2 runs Linux in a lightweight virtual machine with a separate network interface.
Windows can’t automatically access WSL2’s localhost ports unless:

You use --address 0.0.0.0, or

You configure a netsh interface portproxy mapping, or

You run everything directly from Windows (not ideal for Kind).

So, --address 0.0.0.0 bridges that gap — it makes the port visible to your WSL2 IP, which Windows can reach.


When you run:

kubectl port-forward --address 0.0.0.0 svc/camunda-platform-operate 8081:80


This tells kubectl to bind the listening socket to all available network interfaces, not just localhost.

Meaning:

It listens on 127.0.0.1:8081 and

It listens on your WSL2 IP address (e.g., 172.21.213.195:8081)

So, from Windows, you can now reach:
<img width="1536" height="1024" alt="image" src="https://github.com/user-attachments/assets/c7407b4d-d129-4390-83c3-e6962c4d189e" />




Camunda Platform 8 with Spring Boot Demo
---

This project aims at showcasing how the following technologies and architectural principles can be used together in
an example application.

Here is a link to [an article on Medium](https://medium.com/@gushakov/af8733ec0024) which references this repository.

- Camunda Platform 8 (self-managed)
- BPMN workflow
- Spring Boot
- Clean Architecture
- DDD

## How to run

- Start the stack with `docker-compose up -d`
- Deploy BPMN diagram `bpmn/trip-flow.bpmn` to Camunda using Simple Monitor interface
- Run Spring Boot application (default profile) from the IDE

## What is running

- TripFlow applicaiton: http://localhost:8080/trip (username: `customer1` or `customer2`, password: `demo`)
- Camunda Self-Managed instance (with Hazelcast exporter): localhost, ports: 26500 (and others)
- Zeebe Simple Monitor: http://localhost:8082/
- Postgres database: localhost, port 5432, database: `tripflow`

## References

**Special thanks** to Camunda team who gave me some pointers about how to integrate Zeebe's Java client. [This GitHub
repo](https://github.com/camunda-community-hub/camunda-8-lowcode-ui-template) was especially helpful.

:star: As I am writing this, [CamundaCon 2022](https://www.camundacon.com/) is taking place! There is a great
presentation by Luc Weinbrecht, speaking exactly about how to use Camunda and Clean Architecture together.

Here is the list of main references consulted while working on this project. Please, see JavaDoc in the relevant source
code for more detailed references.

1. [Camunda Community Hub, Lowcode UI template example](https://github.com/camunda-community-hub/camunda-8-lowcode-ui-template)
2. [Luc Weinbrecht, GitHub, "Camunda DDD and Clean Architecture"](https://github.com/lwluc/camunda-ddd-and-clean-architecture)
3. [Camunda Platform, Getting Started, Spring Boot client](https://github.com/camunda/camunda-platform-get-started)
4. [Camunda Platform 8, GraphQL API Tasklist, Java client](https://github.com/camunda-community-hub/camunda-tasklist-client-java)
5. ["Ports & Adapters Architecture", Herberto Graça](https://herbertograca.com/2017/09/14/ports-adapters-architecture/)
6. ["Clean Architecture", Robert C. Martin](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)
7. [Clean Domain-Driven Design](https://medium.com/@gushakov/clean-domain-driven-design-2236f5430a05)
8. [Revisiting cargo tracking application using Clean DDD](https://medium.com/@gushakov/revisiting-cargo-tracking-application-using-clean-ddd-4ed16c0e6ae1)
9. [Bernd Rücker, "Navigating Technical Transactions with Camunda 8 and Spring"](https://medium.com/berndruecker/navigating-technical-transactions-with-camunda-8-and-spring-d77d48f16ab9)

## Credits for the sample data and images

To have somewhat realistic data samples for flights, hotels, etc., the following resources (publicly available).

1. [Google Travel](https://www.google.com/travel)
2. [Unsplash images](https://unsplash.com/), please, also consult [Unsplash licence](https://unsplash.com/license)
