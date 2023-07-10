package tw.idv.petradisespringboot.animalfavite.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "Animal_favorite")
public class AnimalFavorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fav_id")
    private Integer favoriteId;

    @Column(name = "animal_id")
    private Integer animalId;

    @Column(name = "mem_id")
    private Integer memId;

    @Override
    public String toString() {
        return "FavoriteItem [favId=" + favoriteId + ", animalId=" + animalId + ", memId=" + memId + "]";
    }


}
