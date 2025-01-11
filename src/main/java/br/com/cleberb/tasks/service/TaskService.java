package br.com.cleberb.tasks.service;

import br.com.cleberb.tasks.model.Task;
import br.com.cleberb.tasks.repository.TaskCustomRepository;
import br.com.cleberb.tasks.repository.TaskRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    //criar uma lista para armazenar as tasks
    private final TaskRepository taskRepository;

    private final TaskCustomRepository taskCustomRepository;

    public TaskService(TaskRepository taskRepository, TaskCustomRepository taskCustomRepository) {
        this.taskRepository = taskRepository;
        this.taskCustomRepository = taskCustomRepository;
    }

    //criar um método para inserir uma task
    //o método deve retornar um Mono(Webflux) task
    //qdo chamar esse método passando uma tarefa, ele vai fazer uma chamada no fluxo, chamar um método reativo(save)
    public Mono<Task> insert(Task task){ //vamos receber como parâmetro uma task
        return Mono.just(task)
                .map(Task::insert)
                .flatMap(this::save);

    }

    public Page<Task> findPaginated (Task task, Integer pageNumber, Integer pageSize){
        return taskCustomRepository.findPaginated(task, pageNumber, pageSize);
    }

    //método para salvar, que recebe uma task
    //este método tbm vai chamar o fluxo e adicionar a task na lista
    private Mono<Task> save (Task task){
        return Mono.just(task) //dá um start no objeto para começar a trabalhar com ele
                .map(taskRepository::save); //transforma esse objeto em uma nova task
    }
}
