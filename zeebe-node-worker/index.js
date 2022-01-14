const { ZBClient, Duration } = require('zeebe-node')

const zbc = new ZBClient("zeebe-cluster-helm-zeebe-gateway.zeebe.svc.cluster.local", {
    retry: true,
    maxRetries: -1, // infinite retries
    maxRetryTimeout: Duration.seconds.of(5)
});

topics = [
'firstJobJS',
'secondJobJS',
'finishJS',
]

topics.forEach(element => {
    zbc.createWorker({
        taskType: element,
        taskHandler:  (job) => {
            console.log("Key: "+job.key+ " Type: "+job.type);
            job.complete()
        }
    })
});

