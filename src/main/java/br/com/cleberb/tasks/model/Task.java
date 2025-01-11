package br.com.cleberb.tasks.model;

import br.com.cleberb.tasks.service.TaskService;
import org.springframework.data.annotation.Id;

public class Task {

    @Id
    private String id;
    private String title;
    private String description;
    private int priority;
    private TaskState state;

    public Task() {
    }

    //construtor recebendo um builder
    //vai atribuir os atributos de builder para os da classe task
    public Task(Builder builder) {
        this.id = builder().id;
        this.title = builder.title;
        this.description = builder.description;
        this.priority = builder.priority;
        this.state = builder.state;
    }

    //método onde pego o objeto task que recebo por parâmetro, incluíndo o status dele e gerando a criação
    public Task insert(){
        return builderFrom(this)
                .withState(TaskState.INSERT)
                .build();
    }

    //não precusamos dos métodos "set" pois utilizaremos o builder
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }

    public TaskState getState() {
        return state;
    }

    //método para inicializar um builder na classe task
    public static Builder builder(){
        return new Builder();
    }

    //método para criar um builder já com um atributo
    public static Builder builderFrom(Task task){
        return new Builder(task);
    }

    public static class Builder{

        private String id;
        private String title;
        private String description;
        private int priority;
        private TaskState state;

        public Builder() {
        }

        public Builder(Task task) {
            this.id = task.id;
            this.title = task.title;
            this.description = task.description;
            this.priority = task.priority;
            this.state = task.state;
        }

        //método para atribuir valor aos atributos da classe Builder
        public Builder withId(String id){
            this.id = id;
            return this;
        }

        public Builder withTitle(String title){
            this.title = title;
            return this;
        }

        public Builder withDescription(String description){
            this.description = description;
            return this;
        }

        public Builder withPriority(int priority){
            this.priority = priority;
            return this;
        }

        public Builder withState(TaskState state){
            this.state = state;
            return this;
        }

        //criar um método Builder e precisa retornar uma task
        public Task build(){
            return new Task(this);
        }
    }
}
