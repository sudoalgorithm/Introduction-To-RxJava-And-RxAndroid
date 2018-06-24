## What is Reactive Programming
Reactive Programming is basically event-based asynchronous programming. Everything you see is an asynchronous data stream, which can be observed and an action will be taken place when it emits values. You can create data stream out of anything; variable changes, click events, http calls, data storage, errors and what not. When it says asynchronous, that means every code module runs on its own thread thus executing multiple code blocks simultaneously.

An advantage of asynchronous approach is, as every task runs on its own thread, all the task can start simultaneously and amount of time takes complete all the tasks is equivalent to the longer task in the list. When it comes to mobile apps, as the tasks runs on background thread, you can achieve seamless user experience without blocking main thread.

- A simple example (from Wikipedia) can be  x = y + z; where the sum of y and z is assigned to x. In reactive programming, when the y value changes, the value of x will be updated automatically without re-executing the x = y + z statement. This can be achieved by observing the value of y or z.
- An array list can be a data steam and an action can be taken when each item it emitted. May be you want to filter out the even numbers and ignoring the odd numbers. This can be done using usual loops and conditional statements, but in reactive programming you can achieve this in a completely different approach.

When you start your app in Reactive Programming, the way you design your architecture and the way you write code changes completely. It’s even becomes more powerful when met with Clean Architecture, MVP, MVVM and other design patters.


## Reactive Extensions
Reactive Extensions (ReactiveX or RX) is a library that follows Reactive Programming principles i.e compose asynchronous and event based programs by using observable sequence. These libraries provides set of interfaces and methods which helps developers write clean and simpler code.

Reactive Extensions are available in multiple languages C++ (RxCpp), C# (Rx.NET), Java (RxJava), Kotlin (RxKotlin), Swift (RxSwift) and lot more. We specifically interested in RxJava and RxAndroid as android is our focused area.


## What is RxJava
RxJava is Java implementation of Reactive Extension (from Netflix). Basically it’s a library that composes asynchronous events by following Observer Pattern. You can create asynchronous data stream on any thread, transform the data and consumed it by an Observer on any thread. The library offers wide range of amazing operators like map, combine, merge, filter and lot more that can be applied onto data stream.

You will understand more about operators and transformations when you start working on actual code examples.


## What is RxAndroid
RxAndroid is specific to Android Platform with few added classes on top of RxJava. More specifically, Schedulers are introduced in RxAndroid (AndroidSchedulers.mainThread()) which plays major role in supporting multithreading concept in android applications. Schedulers basically decides the thread on which a particular code runs whether on background thread or main thread. Apart from it everything we use is from RxJava library only.

Even through there are lot of Schedulers available, Schedulers.io() and AndroidSchedulers.mainThread() are extensively used in android programming. Below are the list of schedulers available and their brief introduction.

- Schedulers.io() – This is used to perform non CPU-intensive operations like making network calls, reading disc / files, database operations etc., This maintains pool of threads.
- AndroidSchedulers.mainThread() – This provides access to android Main Thread / UI Thread. Usually operations like updating UI, user interactions happens on this thread. We shouldn’t perform any intensive operations on this thread as it makes the app glitchy or ANR dialog can be thrown.
- Schedulers.newThread() – Using this, a new thread will be created each time a task is scheduled. It’s usually suggested not to use schedular unless there is a very long running operation. The threads created via newThread() won’t be reused.
- Schedulers.computation() – This schedular can be used to perform CPU-intensive operations like processing huge data, bitmap processing etc., The number of threads created using this scheduler completely depends on number CPU cores available.
- Schedulers.single() – This scheduler will execute all the tasks in sequential order they are added. This can be used when there is necessity of sequential execution is required.
- Schedulers.immediate() – This scheduler executes the the task immediately in synchronous way by blocking the main thread.
- Schedulers.trampoline() – It executes the tasks in First In – First Out manner. All the scheduled tasks will be executed one by one by limiting the number of background threads to one.
- Schedulers.from() – This allows us to create a scheduler from an executor by limiting number of threads to be created. When thread pool is occupied, tasks will be queued.

## RxJava Basics: Observable, Observer
RxJava is all about two key components: Observable and Observer. In addition to these, there are other things like Schedulers, Operators and Subscription.

### Observable: Observable is a data stream that do some work and emits data.

### Observer: Observer is the counter part of Observable. It receives the data emitted by Observable.

### Subscription: The bonding between Observable and Observer is called as Subscription. There can be multiple Observers subscribed to a single Observable.

### Operator / Transformation: Operators modifies the data emitted by Observable before an observer receives them.

### Schedulers: Schedulers decides the thread on which Observable should emit the data and on which Observer should receives the data i.e background thread, main thread etc.
