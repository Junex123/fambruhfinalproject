package coms.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import coms.model.product.Product;
import coms.model.product.ProductImageMain;
import coms.model.product.ProductSize;
import coms.model.product.comboproduct;
import coms.repository.ComboProductRepository;

import coms.repository.ProductRepo;

import coms.repository.Sizerepo;
import coms.repository.UserRepo;
import coms.repository.mainimagerepo;
import coms.repository.wishlistrepository;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepo productRepo;


    @Autowired
    private ComboProductRepository comboProductRepository;
    
    @Autowired
    private UserRepo userrepo;
    
    @Autowired
    private mainimagerepo imagerepo;
    
    private final String FOLDER_PATH = "C:\\Users\\JunaidShaikh\\Desktop\\FILE_PATH_FAMBRUH";



    
    @Autowired
    private wishlistrepository wishlistrepo;
    
    @Autowired
    private Sizerepo sizerepo;

    // Add a product
    public Product addProduct(Product product) {
        return this.productRepo.save(product);
    }
    
    // Find a product by ID
    public Product findProduct(Long pid) {
        return this.productRepo.findById(pid).orElse(null);
    }
    
    // Find all products
    public List<Product> findAllProducts() {
        return this.productRepo.findAll();
    }
    
    // Find products by name or salt
    public List<Product> findByNameOrSalt(String name, String salt) {
        return this.productRepo.findByNameContainingIgnoreCaseOrSaltContainingIgnoreCase(name, salt);
    }
    
    // Delete a product by ID
    public void deleteProductById(Long pid) {
        this.productRepo.deleteById(pid);
    }
    
    // Find available products by name
    public List<Product> findTrueProduct(String name) {
        return this.productRepo.findByNameAndIsAvailableTrue(name);
    }


  



    // Combo Product Services

    // Add a combo product
    public comboproduct addComboProduct(comboproduct comboProduct) {
        return this.comboProductRepository.save(comboProduct);
    }

    // Find all combo products
    public List<comboproduct> findAllComboProducts() {
        return this.comboProductRepository.findAll();
    }

    // Other Product Services (if any)
    // Find a combo product by ID
    public comboproduct findComboProduct(Long id) {
        return this.comboProductRepository.findById(id).orElse(null);
    }

    // Delete a combo product by ID
    public void deleteComboProductById(Long id) {
        this.comboProductRepository.deleteById(id);
    }
    // ...
    public String uploadImageToFileSystem(MultipartFile file) throws IOException {
        String filePath=FOLDER_PATH+file.getOriginalFilename();

        ProductImageMain fileData=imagerepo.save(ProductImageMain.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .filePath(filePath).build());

        file.transferTo(new File(filePath));

        if (fileData != null) {
            return "file uploaded successfully : " + filePath;
        }
        return null;
    }
    
}
