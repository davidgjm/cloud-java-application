# Graceful shutdown

## JVM Shutdown



## JVM shutdown hook
API doc in [`Runtime.addShutdownHook`](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/Runtime.html#addShutdownHook(java.lang.Thread))

> The Java virtual machine shuts down in response to two kinds of events:
>
> The program exits normally, when the last non-daemon thread exits or when the exit (equivalently, System.exit) method is invoked, or
> The virtual machine is terminated in response to a user interrupt, such as typing > C, or a system-wide event, such as user logoff or system shutdown.
> A shutdown hook is simply an initialized but unstarted thread. When the virtual machine begins its shutdown sequence it will start all registered shutdown hooks in some unspecified order and let them run concurrently. When all the hooks have finished it will then halt. Note that daemon threads will continue to run during the shutdown sequence, as will non-daemon threads if shutdown was initiated by invoking the exit method.
>
> Once the shutdown sequence has begun it can be stopped only by invoking the halt method, which forcibly terminates the virtual machine.
>
> Once the shutdown sequence has begun it is impossible to register a new shutdown hook or de-register a previously-registered hook. Attempting either of these operations will cause an IllegalStateException to be thrown.
>
> Shutdown hooks run at a delicate time in the life cycle of a virtual machine and should therefore be coded defensively. They should, in particular, be written to be thread-safe and to avoid deadlocks insofar as possible. They should also not rely blindly upon services that may have registered their own shutdown hooks and therefore may themselves in the process of shutting down. Attempts to use other thread-based services such as the AWT event-dispatch thread, for example, may lead to deadlocks.
>
> Shutdown hooks should also finish their work quickly. When a program invokes exit the expectation is that the virtual machine will promptly shut down and exit. When the virtual machine is terminated due to user logoff or system shutdown the underlying operating system may only allow a fixed amount of time in which to shut down and exit. It is therefore inadvisable to attempt any user interaction or to perform a long-running computation in a shutdown hook.
>
> Uncaught exceptions are handled in shutdown hooks just as in any other thread, by invoking the uncaughtException method of the thread's ThreadGroup object. The default implementation of this method prints the exception's stack trace to System.err and terminates the thread; it does not cause the virtual machine to exit or halt.
>
> In rare circumstances the virtual machine may abort, that is, stop running without shutting down cleanly. This occurs when the virtual machine is terminated externally, for example with the SIGKILL signal on Unix or the TerminateProcess call on Microsoft Windows. The virtual machine may also abort if a native method goes awry by, for example, corrupting internal data structures or attempting to access nonexistent memory. If the virtual machine aborts then no guarantee can be made about whether or not any shutdown hooks will be run.


## Spring Boot Shutdown

### Configuration

#### `application.yml`
```yaml
server:
  shutdown: graceful
```

## Kubernetes container lifecycle

The graceful shutdown feature should be enabled first in Spring Boot

See details in [Kubernetes Container Lifecycle](https://docs.spring.io/spring-boot/docs/current/reference/html/deployment.html#deployment.cloud.kubernetes.container-lifecycle)

```yaml
spec:
  containers:
  - name: "example-container"
    image: "example-image"
    lifecycle:
      preStop:
        exec:
          command: ["sh", "-c", "sleep 10"]
```