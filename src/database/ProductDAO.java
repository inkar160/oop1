package database;

import model.Product;
import model.FoodProduct;
import model.HouseholdProduct;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    public boolean insertProduct(Product product) {
        String sql = "INSERT INTO products (name, price, product_type, expired, usage_type) VALUES (?, ?, ?, ?, ?)";
        Connection connection = DatabaseConnection.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());

            if (product instanceof FoodProduct) {
                statement.setString(3, "FOOD");
                statement.setBoolean(4, ((FoodProduct) product).isExpired());
                statement.setString(5, null);
            } else if (product instanceof HouseholdProduct) {
                statement.setString(3, "HOUSEHOLD");
                statement.setNull(4, Types.BOOLEAN);
                statement.setString(5, ((HouseholdProduct) product).getUsageType());
            }

            int rowsInserted = statement.executeUpdate();
            statement.close();

            if (rowsInserted > 0) {
                System.out.println("Product inserted successfully!");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Insert failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
        return false;
    }

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products ORDER BY product_id";
        Connection connection = DatabaseConnection.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Product product = extractProductFromResultSet(resultSet);
                if (product != null) {
                    products.add(product);
                }
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Select failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return products;
    }

    public Product getProductById(int productId) {
        String sql = "SELECT * FROM products WHERE product_id = ?";
        Connection connection = DatabaseConnection.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, productId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Product product = extractProductFromResultSet(resultSet);
                resultSet.close();
                statement.close();
                return product;
            }
        } catch (SQLException e) {
            System.out.println("Get product failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return null;
    }

    public boolean updateProduct(Product product) {
        String sql = "UPDATE products SET name = ?, price = ?, expired = ?, usage_type = ? WHERE product_id = ?";
        Connection connection = DatabaseConnection.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.setInt(5, product.getId());

            if (product instanceof FoodProduct) {
                statement.setBoolean(3, ((FoodProduct) product).isExpired());
                statement.setString(4, null);
            } else if (product instanceof HouseholdProduct) {
                statement.setNull(3, Types.BOOLEAN);
                statement.setString(4, ((HouseholdProduct) product).getUsageType());
            }

            int rowsUpdated = statement.executeUpdate();
            statement.close();

            if (rowsUpdated > 0) {
                System.out.println("Product updated successfully!");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Update failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return false;
    }

    public boolean deleteProduct(int productId) {
        String sql = "DELETE FROM products WHERE product_id = ?";
        Connection connection = DatabaseConnection.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, productId);

            int rowsDeleted = statement.executeUpdate();
            statement.close();

            if (rowsDeleted > 0) {
                System.out.println("Product deleted successfully!");
                return true;
            } else {
                System.out.println("No product found with ID: " + productId);
            }
        } catch (SQLException e) {
            System.out.println("Delete failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return false;
    }

    public List<Product> searchByName(String name) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE name ILIKE ? ORDER BY name";
        Connection connection = DatabaseConnection.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + name + "%");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Product product = extractProductFromResultSet(resultSet);
                if (product != null) {
                    products.add(product);
                }
            }

            resultSet.close();
            statement.close();
            System.out.println("Found " + products.size() + " products");
        } catch (SQLException e) {
            System.out.println("Search failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return products;
    }

    public List<Product> searchByPriceRange(double minPrice, double maxPrice) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE price BETWEEN ? AND ? ORDER BY price DESC";
        Connection connection = DatabaseConnection.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDouble(1, minPrice);
            statement.setDouble(2, maxPrice);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Product product = extractProductFromResultSet(resultSet);
                if (product != null) {
                    products.add(product);
                }
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return products;
    }

    public List<Product> searchByMinPrice(double minPrice) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE price >= ? ORDER BY price DESC";
        Connection connection = DatabaseConnection.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDouble(1, minPrice);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Product product = extractProductFromResultSet(resultSet);
                if (product != null) {
                    products.add(product);
                }
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return products;
    }

    private Product extractProductFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("product_id");
        String name = resultSet.getString("name");
        double price = resultSet.getDouble("price");
        String productType = resultSet.getString("product_type");

        if ("FOOD".equals(productType)) {
            boolean expired = resultSet.getBoolean("expired");
            FoodProduct foodProduct = new FoodProduct(id, name, price);
            foodProduct.setExpired(expired);
            return foodProduct;
        } else if ("HOUSEHOLD".equals(productType)) {
            String usageType = resultSet.getString("usage_type");
            HouseholdProduct householdProduct = new HouseholdProduct(id, name, price);
            householdProduct.setUsageType(usageType);
            return householdProduct;
        }

        return null;
    }

    public void displayAllProducts() {
        List<Product> products = getAllProducts();

        if (products.isEmpty()) {
            System.out.println("No products in the store");
            return;
        }

        System.out.println("\n===== ALL PRODUCTS IN STORE =====");
        for (Product product : products) {
            product.displayInfo();
            System.out.println("Category: " + product.getCategory());

            if (product instanceof FoodProduct) {
                System.out.println("Expired: " + ((FoodProduct) product).isExpired());
            } else if (product instanceof HouseholdProduct) {
                System.out.println("Usage Type: " + ((HouseholdProduct) product).getUsageType());
            }

            System.out.println("----------------------");
        }
        System.out.println("Total products: " + products.size());
    }
}