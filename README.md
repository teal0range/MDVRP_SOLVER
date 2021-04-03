# MDVRP

### 目录

```java
main
 ─java
   │  Entry.java
   │
   ├─Algorithm
   │      Generator.java // Abstract Class
   │      GreedyGenerator.java // <- Generator
   │      InitializeSolution.java // TODO：调用Generator
   │      MainAlgo.java 
   │
   ├─Common
   │  │  Problem.java //include(Nodes,Routes)
   │  │  Route.java // List<Node> begin=end
   │  │  Solution.java //include(List<Route>,List<Customer>,Problem)
   │  │
   │  └─Node
   │         Customer.java // <- Node
   │         Depot.java // <- Node
   │         Node.java
   │
   ├─Constraints // Time(duration), Need(Weight)
   │  │  Constraint.java
   │  │  ConstraintManager.java
   │  │  HardConstraint.java
   │  │  HardTimeConstraint.java
   │  │  HardWeightConstraint.java
   │  │  SoftConstraint.java
   │  │  SoftCostConstraint.java
   │  │
   │  ├─InsertionConstraints
   │  │      InsertionConstraintManager.java
   │  │      InsertionHardTimeConstraint.java
   │  │      InsertionHardWeightConstraint.java
   │  │      InsertionSoftCostConstraint.java
   │  │
   │  └─SubstituteConstraints
   │          SubstituteConstraintManager.java
   │          SubstituteHardTimeConstraint.java
   │          SubstituteHardWeightConstraint.java
   │          SubstituteSoftCostConstraint.java
   │
   ├─IO
   │      CourdeauInstanceReader.java
   │      DataReader.java
   │
   └─Operators
           BaseOperator.java
           InnerOperator.java
           InnerShift10.java
          *OperateContext.java //include(Problem,mainRoute,operateNodes,operatePos)
```

need = weights



### Reference

[1] A VNS-Based Algorithm with Adaptive Local Search for Solving the Multi-Depot Vehicle Routing Problem[M].