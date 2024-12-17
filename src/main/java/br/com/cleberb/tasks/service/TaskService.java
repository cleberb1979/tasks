package br.com.cleberb.tasks.service;

import br.com.cleberb.tasks.model.Task;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class TaskService {

    //criar uma lista para armazenar as tasks
    public static List<Task> taskList;

    //criar um método para inserir uma task
    //o método deve retornar um Mono(Webflux) task
    //qdo chamar esse método passando uma tarefa, ele vai fazer uma chamada no fluxo, chamar um método reativo(save)
    public Mono<Task> insert(Task task){ //vamos receber como parâmetro uma task
        return Mono.just(task)
                .flatMap(this::save);

    }

    //método para salvar, que recebe uma task
    //este método tbm vai chamar o fluxo e adicionar a task na lista
    private Mono<Task> save (Task task){
        return Mono.just(task) //dá um start no objeto para começar a trabalhar com ele
                .map(Task::newTask); //transforma esse objeto em uma nova task
    }
}
