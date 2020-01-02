package Lambda;

public class FilterEmpolyeeByAge implements MyPredicate<Employee> {
    @Override
    public boolean test(Employee employee) {
        return employee.getAge()>=15;
    }
}
