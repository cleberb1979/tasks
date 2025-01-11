package br.com.cleberb.tasks.controller.converter;

import br.com.cleberb.tasks.controller.dto.TaskDTO;
import br.com.cleberb.tasks.model.Task;
import br.com.cleberb.tasks.model.TaskState;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class TaskDTOConverter {

    //métodos: um para transformar objetos de model para dto e um para transformar dto para model
    //model para dto: estamos recebendo model (Task) e retornando um dto (TaskDTO)
    public TaskDTO convert(Task task){
        return Optional.ofNullable(task)
                .map(source -> {
                    TaskDTO dto = new TaskDTO();
                    dto.setId(source.getId());
                    dto.setTitle(source.getTitle());
                    dto.setDescription(source.getDescription());
                    dto.setPriority(source.getPriority());
                    dto.setState(source.getState());
                    return dto;
                }).orElse(null);
    }

    //dto para model: estamos recebendo dto (TaskDTO) e retornando um task (Task)
    //em model, podemos utilizar o builder
    public Task convert(TaskDTO taskDTO){
        return Optional.ofNullable(taskDTO)
                .map(source -> Task.builder()
                        .withId(source.getId())
                        .withTitle(source.getTitle())
                        .withDescription(source.getDescription())
                        .withPriority(source.getPriority())
                        .withState(source.getState())
                        .build())
                .orElse(null);
    }

    //método para converter todos os atributos de uma tafera em uma tarefa
    public Task convert(String id, String title, String description, int priority, TaskState taskState){
        return Task.builder()
                .withId(id)
                .withTitle(title)
                .withDescription(description)
                .withPriority(priority)
                .withState(taskState)
                .build();
    }

    //método para converter uma lista de model para uma lista de dto
    public List<TaskDTO> convertList(List<Task> taskList){
        return Optional.ofNullable(taskList)
                .map(array -> array.stream().map(this::convert).collect(Collectors.toList()))//percorrendo todos os objetos da lista e por fim, coletar todos
                .orElse(new ArrayList<>());
    }
}
