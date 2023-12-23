package studUPT.VladBudiu.emsbakcend.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import studUPT.VladBudiu.emsbakcend.dto.EmployeeDto;
import studUPT.VladBudiu.emsbakcend.entity.Employee;
import studUPT.VladBudiu.emsbakcend.exception.ResourceNotFoundExsception;
import studUPT.VladBudiu.emsbakcend.mapper.EmployeeMapper;
import studUPT.VladBudiu.emsbakcend.repository.EmployeeRepository;
import studUPT.VladBudiu.emsbakcend.service.EmployeeService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {

        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {

        Employee foundEmployee = employeeRepository.findById(employeeId)
                .orElseThrow(() ->
                        new ResourceNotFoundExsception("Employee does not exist with this id: "+ employeeId));

        return EmployeeMapper.mapToEmployeeDto(foundEmployee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {

        List<Employee> employees = employeeRepository.findAll();


        return employees.stream()
                .map((employee) -> EmployeeMapper.mapToEmployeeDto(employee))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee) {

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundExsception("Employee does not exist in the database with this id: "+ employeeId));
        employee.setFirstName(updatedEmployee.getFirstName());
        employee.setLastName(updatedEmployee.getLastName());
        employee.setEmail(updatedEmployee.getEmail());

        Employee updatedEmployeeObj = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(updatedEmployeeObj) ;
    }

    @Override
    public void deleteEmployee(Long employeeId) {

        Employee toBeDeletedEmployee= employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundExsception("Employee does not exist in the database with this id: \"+ employeeId"));

        employeeRepository.delete(toBeDeletedEmployee);
    }


}
