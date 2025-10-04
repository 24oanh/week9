package ltweb.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Category")
public class CategoryEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(columnDefinition = "nvarchar(255)")
    private String categoryName;
    
    @Column(columnDefinition = "nvarchar(255)")
    private String categoryThumb;
    
    @Column(columnDefinition = "nvarchar(MAX)")
    private String categoryDescription;
    
    // Additional getter/setter for compatibility with controller
    public String getName() {
        return categoryName;
    }
    
    public void setName(String name) {
        this.categoryName = name;
    }
    
    public String getDescription() {
        return categoryDescription;
    }
    
    public void setDescription(String description) {
        this.categoryDescription = description;
    }
}