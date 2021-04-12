# MDVRP

### 目录

```java
├─main
│  ├─java
│  │  │  Entry.java
│  │  │
│  │  ├─Algorithm
│  │  │      Generator.java
│  │  │      GreedyGenerator.java
│  │  │      InitializeSolution.java
│  │  │      VNSALS.java
│  │  │
│  │  ├─Common
│  │  │      Customer.java
│  │  │      Depot.java
│  │  │      Node.java
│  │  │      Problem.java
│  │  │      Route.java
│  │  │      Solution.java
│  │  │
│  │  ├─Constraints
│  │  │  │  Constraint.java
│  │  │  │  HardConstraint.java
│  │  │  │  HardConstraintManager.java
│  │  │  │  HardTimeConstraint.java
│  │  │  │  HardWeightConstraint.java
│  │  │  │  SoftConstraint.java
│  │  │  │  SoftConstraintManager.java
│  │  │  │  SoftCostConstraint.java
│  │  │  │
│  │  │  ├─Insertion
│  │  │  │      HardTimeConstraintImpl.java
│  │  │  │      HardWeightConstraintImpl.java
│  │  │  │      SoftCostConstraintImpl.java
│  │  │  │
│  │  │  ├─Shift10
│  │  │  │      HardTimeConstraintImpl.java
│  │  │  │      HardWeightConstraintImpl.java
│  │  │  │      SoftCostConstraintImpl.java
│  │  │  │
│  │  │  ├─Shift20
│  │  │  │      HardTimeConstraintImpl.java
│  │  │  │      HardWeightConstraintImpl.java
│  │  │  │      SoftCostConstraintImpl.java
│  │  │  │
│  │  │  ├─Swap11
│  │  │  │      HardTimeConstraintImpl.java
│  │  │  │      HardWeightConstraintImpl.java
│  │  │  │      SoftCostConstraintImpl.java
│  │  │  │
│  │  │  ├─Swap21
│  │  │  │      HardTimeConstraintImpl.java
│  │  │  │      HardWeightConstraintImpl.java
│  │  │  │      SoftCostConstraintImpl.java
│  │  │  │
│  │  │  ├─Swap22
│  │  │  │      HardTimeConstraintImpl.java
│  │  │  │      HardWeightConstraintImpl.java
│  │  │  │      SoftCostConstraintImpl.java
│  │  │  │
│  │  │  ├─TwoOpt
│  │  │  │      HardTimeConstraintImpl.java
│  │  │  │      HardWeightConstraintImpl.java
│  │  │  │      SoftCostConstraintImpl.java
│  │  │  │
│  │  │  ├─TwoOptStar1
│  │  │  │      HardTimeConstraintImpl.java
│  │  │  │      HardWeightConstraintImpl.java
│  │  │  │      SoftCostConstraintImpl.java
│  │  │  │
│  │  │  └─TwoOptStar2
│  │  │          HardTimeConstraintImpl.java
│  │  │          HardWeightConstraintImpl.java
│  │  │          SoftCostConstraintImpl.java
│  │  │
│  │  ├─IO
│  │  │      BasicConfig.java
│  │  │      ConfigReader.java
│  │  │      CourdeauInstanceReader.java
│  │  │      DataReader.java
│  │  │      IConfigReader.java
│  │  │
│  │  ├─Operators
│  │  │      ClusterRefinement1.java
│  │  │      ClusterRefinement2.java
│  │  │      ClusterRefinement3.java
│  │  │      ClusterRefinementCriteria.java
│  │  │      ClusterRefinementManager.java
│  │  │      ConstrainedOpt.java
│  │  │      Insertion.java
│  │  │      IPerturbation.java
│  │  │      OperationContext.java
│  │  │      OperationSelector.java
│  │  │      Operator.java
│  │  │      OperatorManager.java
│  │  │      RandomRuin.java
│  │  │      RecreatePerturbation.java
│  │  │      Ruin.java
│  │  │      SequentialPerturbation.java
│  │  │      Shift10.java
│  │  │      Shift20.java
│  │  │      Swap11.java
│  │  │      Swap21.java
│  │  │      Swap22.java
│  │  │      TwoOpt.java
│  │  │      TwoOptStar1.java
│  │  │      TwoOptStar2.java
│  │  │
│  │  └─Utils
│  │          RandomController.java
│  │          TimeController.java
│  │
│  └─resources
│      │  config.json
│      │  log4j.properties
│      │
│      └─Data
└─test
    └─java
        │  EntryTest.java
        │
        ├─Algorithm
        │      InitialSolutionGeneratorTest.java
        │      VNSALSTest.java
        │
        ├─Common
        │      RouteTest.java
        │
        ├─Constraints
        │  ├─Shift10
        │  │      SoftCostConstraintImplTest.java
        │  │
        │  ├─Shift20
        │  │      SoftCostConstraintImplTest.java
        │  │
        │  ├─Swap11
        │  │      SoftCostConstraintImplTest.java
        │  │
        │  ├─Swap21
        │  │      SoftCostConstraintImplTest.java
        │  │
        │  ├─Swap22
        │  │      SoftCostConstraintImplTest.java
        │  │
        │  ├─TwoOpt
        │  │      SoftCostConstraintImplTest.java
        │  │
        │  ├─TwoOptStar1
        │  │      SoftCostConstraintImplTest.java
        │  │
        │  └─TwoOptStar2
        │          SoftCostConstraintImplTest.java
        │
        ├─IO
        │      CourdeauInstanceReaderTest.java
        │
        └─Operators
                operationContextTest.java
                OperatorManagerTest.java
```



### Reference

[1] A VNS-Based Algorithm with Adaptive Local Search for Solving the Multi-Depot Vehicle Routing Problem[M].