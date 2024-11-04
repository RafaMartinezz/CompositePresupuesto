# Construction Project Cost Breakdown

This Java project represents a hierarchical cost structure for a construction project, such as building a house. Using the **Composite design pattern**, the project organizes the components of a house (e.g., structure, interior, and garden) into a tree structure of parts, each with an associated cost. The application can calculate and display the total cost of the project, as well as a detailed breakdown of each component.

## Table of Contents

- [Overview](#overview)
- [Purpose](#purpose)
- [Features](#features)
- [Class Structure](#class-structure)
- [Example Usage](#example-usage)
- [Explanation](#explanation)

---

## Overview

The project models components of a construction project, using composite objects to group related parts and leaf objects to represent individual parts with specific costs. By using the Composite pattern, the project can calculate the total cost of a component by recursively summing the costs of its subcomponents. This structure allows easy expansion and detailed cost analysis for complex projects.

## Purpose

This project serves as a **learning exercise** in:
- Implementing the **Composite design pattern** to represent hierarchical structures with individual and composite elements.
- Modeling **recursive cost calculations** using composite and leaf objects.
- Managing **flexible cost structures** for complex projects with nested parts and components.

## Features

- **Composite Design Pattern**: Organizes components in a tree structure, where each part can be an individual item or a composite of multiple items.
- **Recursive Cost Calculation**: Calculates the total cost for any component by summing the costs of its subcomponents.
- **Detailed Cost Breakdown**: Prints a hierarchical view of the project structure, showing the cost of each part.

## Class Structure

- **`ParteAbstracta`**: Abstract class representing a project part. Defines methods for getting the part's name, setting its name, calculating its cost (`getPrecio`), and printing its cost breakdown (`imprimirPresupuesto`).
- **`ParteCompuesta`**: A composite part that can contain other `ParteAbstracta` objects. Implements recursive cost calculation by summing the costs of its contained parts.
- **`ParteSimple`**: A leaf part with a fixed cost. Represents individual items that do not contain other parts.
- **`App`**: Main application class that initializes a sample construction project structure, calculates costs, and displays a detailed breakdown of the project.

## Example Usage

In the example usage below, the main method in `App` initializes a sample house construction project with various components and subcomponents:

```java
// Creating individual parts
ParteSimple cierre = new ParteSimple("Cierre finca", 4000);
ParteSimple jardin = new ParteSimple("jardín", 1000);

// Creating composite parts and adding individual parts to them
ParteCompuesta finca = new ParteCompuesta("finca");
finca.addParte(cierre);
finca.addParte(jardin);

// Creating the full house as a composite part and adding all components to it
ParteCompuesta casa = new ParteCompuesta("Casa");
casa.addParte(finca);

// Calculating and printing total cost
System.out.println("Total cost of house: " + casa.getPrecio());
casa.imprimirPresupuesto("");
```

### Example Output

```plaintext
Precio casa 44000.0
Precio finca 5000.0

Casa44000.0
    finca5000.0
        Cierre finca4000.0
        jardín1000.0
    estructura30000.0
        tejado10000.0
        alturas10000.0
        sótano10000.0
    interior9000.0
        habitaciones2000.0
            mobiliario1000.0
            pintura1000.0
```

## Explanation

The `App` class uses the Composite pattern to model a hierarchical structure for a construction project:
1. **Individual Parts**: Each individual part (e.g., `tejado`, `jardín`, `cierre`) is represented by `ParteSimple`, which has a fixed cost.
2. **Composite Parts**: Groups of related parts (e.g., `finca`, `estructura`) are represented by `ParteCompuesta`, which contains a list of `ParteAbstracta` objects (both simple and composite).
3. **Cost Calculation**: Each `ParteCompuesta` calculates its total cost by summing the costs of its subcomponents. The `imprimirPresupuesto` method provides a detailed breakdown of each part and its cost.

This structure allows for a highly flexible and scalable model, ideal for managing complex projects with multiple levels of nested components. Each component, whether simple or composite, can be added, modified, or removed without affecting the rest of the structure.