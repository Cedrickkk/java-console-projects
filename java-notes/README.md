Java is an object-oriented programming language first released in 1995 by Sun Microsystems (now owned by Oracle).

It's designed to be platform-independent through its "**write once, run anywhere**" approach, using the Java Virtual Machine (JVM) to execute code on any device that has a JVM installed.

It features automatic memory management through garbage collection, strong type checking, and extensive libraries that make it versatile for different programming needs.

---

##### **Java Development Kit (JDK)**

A complete development package for Java developers, It Includes the JRE plus development tools like

- Java compiler (javac) to convert source code to bytecode
- Debugger
- Java documentation generator (javadoc)
- Other development utilities and APIs

---

**JVM Runtime Process**
After the Java code is compiled into `.class` file the following things will happen at runtime:

- **Class Loading**: The Class Loader loads all required classes needed to execute the program. It finds and imports binary data for classes.
- **Bytecode Verification**: The Bytecode Verifier checks that the code:
  - Follows proper Java language rules
  - Doesn't violate security restrictions
  - Doesn't access memory improperly
  - Has valid bytecode formats
- **Just-In-Time Compilation**: The JIT compiler identifies frequently executed code ("hot spots") and converts them to native machine code.
- **Execution**: The Java Virtual Machine executes the program by:
  - Interpreting bytecode that hasn't been JIT-compiled
  - Running optimized native code for JIT-compiled sections
  - Managing memory allocation and garbage collection
  - Handling exceptions and errors

---

##### **Java Runtime Environment (JRE)**

The minimum environment required to run Java applications. It contains

- Java Virtual Machine (JVM) that executes Java bytecode
- Core libraries and classes needed for running Java programs
- Does NOT include development tools

![[assets/Pasted image 20250406210651.png]]

##### **Java Virtual Machine**

The Java Virtual Machine (JVM) is an abstract computing machine that enables Java programs to run on any device or operating system without being rewritten.

In simple terms, the JVM acts as an intermediary between Java code and the computer hardware. When you compile Java code, it's converted into bytecode (not machine code), which is then interpreted and executed by the JVM. This is what enables Java's "write once, run anywhere" capability.

##### How the JVM Works

The Java Virtual Machine (JVM) operates through several sequential steps:

1. **Class Loading**
   - Loads compiled `.class` files into memory
   - Performed by three types of class loaders: Bootstrap, Extension, and Application
   - Verifies class file format and security constraints

**Bootstrap Class Loader**

- This is the **parent** of all other class loaders.
- It is implemented in **native code** (not in Java).
- It is responsible for loading the **core Java runtime libraries** (the `rt.jar` and other essential JAR files) located in the `jre/lib` directory (or similar location depending on the JVM implementation).
- It loads fundamental classes like `java.lang.Object`, `java.lang.String`, etc.

**Extension Class Loader**

- This is a **child** of the Bootstrap Class Loader.
- It is responsible for loading classes from the **extension directories** of the JRE. These are typically located in `jre/lib/ext` (or a directory specified by the `java.ext.dirs` system property).

**System Class Loader**

- This is a **child** of the Extension Class Loader.
- It is responsible for loading **application-specific classes** from the classpath specified when the Java application is launched. This classpath can be set using the `-classpath` or `-cp` command-line option, or through the `CLASSPATH` environment variable.
- It loads the `.class` files of your application, as well as any third-party libraries (JAR files) included in your project's dependencies.
- This is the class loader that is typically used to load your main application class. You can access it in your Java code using `ClassLoader.getSystemClassLoader()`.

2. **Linking**
   - **Verification**: Ensures bytecode adheres to JVM specifications
   - **Preparation**: Allocates memory for class variables and initializes them with default values
   - **Resolution**: Replaces symbolic references with direct references from the method area
3. **Initialization**
   - Executes class initialization code (static blocks and static variable assignments)
   - Initializes superclasses first
4. **Runtime Data Areas**
   - **Method Area**: Stores class structures, method code, and constant pool
   - **Heap**: Objects are allocated here, managed by garbage collection
   - **Java Stacks**: One per thread, stores frames for method execution
   - **PC Registers**: Tracks execution position for each thread
   - **Native Method Stacks**: Used for native methods
5. **Execution Engine**
   - **Interpreter**: Reads and executes bytecode instructions one by one
   - **JIT Compiler**: Compiles frequently executed bytecode to native code
   - **Garbage Collector**: Automatically reclaims unused memory
6. **Native Method Interface (JNI)**
   - Enables interaction between Java code and native applications
7. **Native Method Libraries**
   - Collection of C/C++ libraries needed by the execution engine

![[assets/Pasted image 20250406211713.png]]

---

##### Class

In Java, classes are the fundamental building blocks of object-oriented programming. They serve as blueprints or templates for creating objects.

**Key Characteristics of Java Classes**

1. **Blueprint for Objects**: Classes define the structure and behavior that objects of that class will have.

2. **Encapsulation**: Classes combine data (fields/attributes) and methods (functions) into a single unit.

3. **Declaration Syntax**:
   `java
public class ClassName {
	// Fields, constructors, methods
}
    `
4. **Components of a Class**:
   - **Fields**: Variables that store data
   - **Methods**: Functions that define behavior
   - **Constructors**: Special methods for initializing objects
   - **Nested Classes**: Classes defined within another class
5. **Access Modifiers**:
   - `public`: Accessible from anywhere
   - `protected`: Accessible within the package and subclasses
   - `default` (no modifier): Accessible only within the package
   - `private`: Accessible only within the class
6. **Creating Objects**:
   `java
ClassName objectName = new ClassName();
    `
7. **Class vs Object**: A class is like a template, while an object is an instance of that template with actual values.
8. **Special Class Types**:
   - **Abstract Classes**: Cannot be instantiated, may contain abstract methods
   - **Final Classes**: Cannot be subclassed
   - **Static Classes**: Can only exist as nested classes

Classes form the foundation of Java's object-oriented design, enabling code organization, reusability, and modular development.

---

###### `main` method

The main method in Java is the **entry point** of any standalone Java application. It's where the program execution begins.

```java
class Main {
	public static void main(String[] args) {
		System.out.println("This is a main method");
	}
}
```

The main method in Java is the essential starting point for any executable Java application. Its specific `public static void main(String[] args)` signature is required for the JVM to locate and execute the program's code.

---

###### **Access Modifiers**

They are keywords used to control the visibility or accessibility of classes, interfaces, variables, methods, constructors, and other members within a Java program. Essentially, they determine which parts of your code can see and use other parts.

There are four access modifiers in Java, ranging from the most restrictive to the least restrictive:

1. **`private`**: The most restrictive access modifier.
   - Members declared as `private` are only accessible within the **same class** in which they are declared.
   - They are not accessible from outside the class, including subclasses or other classes in the same package.
   - The primary use of `private` is for data hiding (encapsulation), protecting the internal state of an object.

```java
// In a class named 'PrivateClass'
package com.example;

public class PrivateClass {
    private int privateVariable = 40;

    private void privateMethod() {
        System.out.println("This is a private method.");
    }

    public void accessPrivate() {
        System.out.println(this.privateVariable);
        // Accessible from within the same class

        this.privateMethod(); // Accessible from within the same class
    }
}

// In another class in the same package 'com.example'
package com.example;

public class AnotherClassInSamePackage {
    public static void main(String[] args) {
        PrivateClass obj = new PrivateClass();
        // System.out.println(obj.privateVariable);
        // Not accessible (compile-time error)

        // obj.privateMethod(); // Not accessible (compile-time error)

        obj.accessPrivate();
        // Accessible because it's a public method in PrivateClass
    }
}

// In a subclass in a different package 'com.another'
package com.another;

import com.example.PrivateClass;

public class SubclassOfPrivate extends PrivateClass {
    public void tryToAccessPrivate() {
        // System.out.println(this.privateVariable);
        // Not accessible (compile-time error)

        // this.privateMethod(); // Not accessible (compile-time error)
    }
}
```

2. **(`default`) or package-private (no explicit modifier)**: When no explicit access modifier is specified, the member has default (or package-private) access.
   - Members with default access are accessible within the **same package**.
   - They are not accessible from outside the package, even by subclasses in different packages.
   - This level of access is often used to group related classes together and allow them to collaborate closely without exposing their internals to the wider program.

```java
// In a class named 'DefaultClass' in package 'com.example'
package com.example;

class DefaultClass {
    int defaultVariable = 30;

    void defaultMethod() {
        System.out.println("This is a default method.");
    }
}

// In another class in the same package 'com.example'
package com.example;

public class AnotherClassInSamePackage {
    public static void main(String[] args) {
        DefaultClass obj = new DefaultClass();
        System.out.println(obj.defaultVariable); // Accessible
        obj.defaultMethod(); // Accessible
    }
}

// In a class in a different package 'com.another'
package com.another;

import com.example.DefaultClass;

public class AnotherPackageClass {
    public static void main(String[] args) {
        // DefaultClass obj = new DefaultClass();
        // Not accessible (compile-time error)

        // System.out.println(obj.defaultVariable); // Not accessible
        // obj.defaultMethod(); // Not accessible
    }
}
```

3. **`protected`**:
   - Members declared as `protected` are accessible within the **same package**.
   - They are also accessible by **subclasses** (even if they are in a different package).
   - However, they are **not** directly accessible by non-subclass classes in a different package.
   - `protected` access is often used when you want to allow subclasses to inherit and potentially modify the behavior of a superclass while still restricting access from unrelated classes in other packages.

```java
// In a class named 'ParentClass' in package 'com.example'
package com.example;

public class ParentClass {
    protected int protectedVariable = 20;

    protected void protectedMethod() {
        System.out.println("This is a protected method in ParentClass.");
    }
}

// In a subclass named 'ChildClass' in a different package 'com.another'
package com.another;

import com.example.ParentClass;

public class ChildClass extends ParentClass {
    public void accessProtected() {
        System.out.println(this.protectedVariable);
        // Accessible because it's a subclass

        this.protectedMethod(); // Accessible because it's a subclass
    }
}

// In another class in the same package 'com.example'
package com.example;

public class AnotherClassInSamePackage {
    public static void main(String[] args) {
        ParentClass obj = new ParentClass();
        System.out.println(obj.protectedVariable);
        // Accessible because it's in the same package

        obj.protectedMethod(); // Accessible because it's in the same package
    }
}

// In a completely unrelated class in a different package 'com.yet.another'
package com.yet.another;

import com.example.ParentClass;

public class UnrelatedClass {
    public static void main(String[] args) {
        ParentClass obj = new ParentClass();
        // System.out.println(obj.protectedVariable);
        // Not accessible (compile-time error)

        // obj.protectedMethod();
        // Not accessible (compile-time error)
    }
}
```

4. **`public`**: The least restrictive access modifier.
   - Members declared as `public` are accessible from **anywhere** in the program, including classes in the same package, different packages, and subclasses.
   - This modifier is used for members that you want to be part of the public interface of your classes, allowing other parts of the program to interact with them.

```java
// In a class named 'MyClass'
package com.example;

public class MyClass {
    public int publicVariable = 10;

    public void publicMethod() {
        System.out.println("This is a public method.");
    }
}

// In another class, possibly in a different package
package com.another.package;

import com.example.MyClass;

public class AnotherClass {
    public static void main(String[] args) {
        MyClass obj = new MyClass();
        System.out.println(obj.publicVariable); // Accessible
        obj.publicMethod(); // Accessible
    }
}
```

---

`static` it indicates that a particular member(variable, method, block, or nested class) belongs to the class itself rather than to any specific instances. They can be accessed directly through the class name without creating an object

```java
public class BankAccount {
    // Static variable - shared by all accounts
    private static int totalAccounts = 0;

    // Instance variables - unique to each account
    private String accountHolder;
    private double balance;

    // Constructor
    public BankAccount(String accountHolder, double initialDeposit) {
        this.accountHolder = accountHolder;
        this.balance = initialDeposit;
        totalAccounts++; // Increment the static counter
    }

    // Static method
    public static int getNumberOfAccounts() {
        return totalAccounts;
    }

    // Instance method
    public void deposit(double amount) {
        balance += amount;
    }

    // Static method with calculation
    public static double calculateInterest(double principal, double rate) {
        return principal * rate / 100;
    }
}
```

---

###### **Literals**

These are syntactical representations of fixed values that directly appear in your code. They're essentially the way you write values of primitive data types (and strings) in your Java program.

**Integer Literals**

- Regular decimal: `42`, `1000`
- Hexadecimal (base 16): `0x2A`, `0XFF` (prefix with `0x` or `0X`)
- Octal (base 8): `052`, `0777` (prefix with `0`)
- Binary (base 2): `0b101010`, `0B1111` (prefix with `0b` or `0B`)
- Long literals: append `L` or `l` (e.g., `100L`)

**Floating-Point Literals**

- Regular: `3.14`, `2.0`, `.5`
- Scientific notation: `3.14e2` (314), `1.2e-3` (0.0012)
- Float literals: append `F` or `f` (e.g., `3.14f`)
- Double literals: default or append `D` or `d` (e.g., `3.14d`)

**Character Literals**

- Regular: `'A'`, `'7'`, `'$'` (enclosed in single quotes)
- Unicode: `'\u0041'` (Unicode for 'A')
- Escape sequences: `'\n'` (newline), `'\t'` (tab), `'\\'` (backslash)

**String Literals**

- Regular: `"Hello, World!"` (enclosed in double quotes)
- Empty string: `""`
- With escape sequences: `"Line 1\nLine 2"`

**Boolean Literals**

- Only two values: `true`, `false`

**Null Literal**

- Only one value: `null` (represents absence of an object reference)

**Underscore in Numeric Literals** (Java 7+)

- For readability: `1_000_000` (same as 1000000)
- `3.141_592_653`

---

##### **Data Types**

In Java, data types are primarily categorized into two main groups: **primitive types** and **reference types**.

**Primitive Types**
Primitive data types are the most basic data types in Java. They are predefined by the language and directly store a specific type of value in memory. These types are not objects and are handled directly by the Java Virtual Machine (JVM) for efficiency

- There are exactly eight primitive types in Java: `byte`, `short`, `int`, `long`, `float`, `double`, `boolean`, and `char`.
- Each primitive type has a fixed size and range of values.
- When you declare a primitive variable, the actual value is stored in the memory location assigned to that variable.
- Operations on primitive types directly manipulate the stored value.

**Reference Types**
Reference data types, in contrast to primitive types, do not store the value directly in memory. Instead, a reference variable holds the memory address of an object. This object contains the actual data. Reference types are used for objects of classes, `interfaces`, `arrays`, and any other `non-primitive type`.

- Reference variables store the location (memory address) of an object and stored in stack.
- They can hold a special value `null`, indicating that the variable does not currently refer to any object.
- Objects are created dynamically in memory (on the heap).
- When you assign one reference variable to another, both variables point to the same object. Changes made through one reference can affect the object accessed by the other.
- `String` is a special reference type because it's immutable. When you perform operations that seem to modify a String, you are actually creating a new String object.

---

The concept of mutability and immutability is fundamentally about **how data is managed in memory and how changes to a variable affect the underlying data.**

##### **Mutability**

Objects whose state (fields) can be changed after creation.

Operations modify the existing object in memory. Multiple references to the same mutable object will see the changes.

**Examples**

- Most user-defined classes (unless specifically designed to be immutable)
- `StringBuilder`
- `StringBuffer`
- `ArrayList`
- `LinkedList`
- `HashSet`
- `HashMap`
- Other mutable Collection classes
- Arrays (of both primitive and reference types)

```java
class Car { String color = "red"; }
Car c1 = new Car();
Car c2 = c1;
c2.color = "blue"; // c1.color is now also "blue"
```

```java
int[] arr1 = {1, 2, 3};
int[] arr2 = arr1; arr2[0] = 10; // arr1 is now {10, 2, 3}
```

---

##### **Immutability**

Objects whose state cannot be changed after creation. Any operation that seems to modify them creates a new object.

Once created, the object's value remains constant. References to the original immutable object are unaffected by operations on other references.

**Examples**

- **Primitive Types:** `int`, `float`, `boolean`, `char`, `byte`, `short`, `long`, `double`.
- **Wrapper Classes for Primitive Types:** `Integer`, `Float`, `Boolean`, `Character`, `Byte`, `Short`, `Long`, `Double`.
- `String`
- `BigInteger`
- `BigDecimal`
- Classes in the `java.time` package (`LocalDate`, `LocalDateTime`, etc.)

```java
String s1 = "Hello";
String s2 = s1;
s1 = s1 + " World"; // s1 is now "Hello World", s2 is still "Hello"
```

```java
Integer i1 = 10;
Integer i2 = i1;
i1 = 20; // i1 is now 20 (new object), i2 is still 10
```

---
